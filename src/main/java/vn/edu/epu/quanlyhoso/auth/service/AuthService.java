package vn.edu.epu.quanlyhoso.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder; // MỚI THÊM
import org.springframework.stereotype.Service;

import vn.edu.epu.quanlyhoso.auth.dto.request.AuthRequest;
import vn.edu.epu.quanlyhoso.auth.dto.request.RegisterRequest;
import vn.edu.epu.quanlyhoso.auth.dto.response.AuthResponse;
import vn.edu.epu.quanlyhoso.auth.entity.Account;
import vn.edu.epu.quanlyhoso.auth.entity.Role;
import vn.edu.epu.quanlyhoso.auth.repository.AccountRepository;
import vn.edu.epu.quanlyhoso.auth.repository.RoleRepository;
import vn.edu.epu.quanlyhoso.common.security.JwtService;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    // ==========================================
    // PHẦN 1: CÁC BIẾN KHAI BÁO (THÊM MỚI BÊN DƯỚI)
    // ==========================================
    private final AccountRepository repository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    
    // Hai thanh gươm mới dành cho tính năng Đăng Ký
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    // ==========================================
    // PHẦN 2: HÀM LOGIN (GIỮ NGUYÊN 100% THEO LỆNH SẾP)
    // ==========================================
    public AuthResponse authenticate(AuthRequest request) {
        // 1. Quăng vào máy xác thực. Sai mật khẩu là nó văng Exception vỡ mặt ngay chỗ này
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // 2. Mật khẩu đúng rồi thì lôi tài khoản từ DB lên
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();

        // 3. Móc cái quyền (ROLE) ra nhét vào Extra Claims
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole().getRoleName());

        // 4. In vé (Token)
        var jwtToken = jwtService.generateToken(extraClaims, user);

        // 5. Đóng gói lại gửi về cho Controller
        return new AuthResponse(jwtToken, user.getRole().getRoleName());
    }

    
    // PHẦN 3: HÀM REGISTER (MỚI ĐƯỢC ĐẮP VÀO)
    public AuthResponse register(RegisterRequest request) {
        // 1. Check xem Username đã bị thằng nào lấy mất chưa
        if (repository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại trong hệ thống!");
        }

        // 2. Tìm Role trong DB (Không truyền thì mặc định id = 2 là LECTURER)
        Integer roleIdToAssign = (request.getRoleId() != null) ? request.getRoleId() : 2;
        Role role = roleRepository.findById(roleIdToAssign)
        .orElseThrow(() -> new RuntimeException("Không tìm thấy quyền (Role) này trong DB!")); 

        // 3. Khởi tạo đối tượng Account mới
        Account account = new Account();
        account.setUsername(request.getUsername());
        account.setEmail(request.getEmail());
        
        // CỰC KỲ QUAN TRỌNG: Mật khẩu trước khi xuống DB phải được băm nát bét (Mã hóa)
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        
        account.setRole(role);
        account.setIsActive(true); // Khởi tạo xong cho Active luôn

        // 4. Ném vào két sắt (Lưu xuống MySQL)
        repository.save(account);

        // 5. Đóng gói Extra Claims y hệt như hàm Login để màng lọc JWT đọc được
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", role.getRoleName());

        // 6. In thẻ Token cấp luôn cho người dùng
        var jwtToken = jwtService.generateToken(extraClaims, account);
        
        return new AuthResponse(jwtToken, role.getRoleName());
    }
}