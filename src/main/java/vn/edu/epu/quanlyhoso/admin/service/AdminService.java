package vn.edu.epu.quanlyhoso.admin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import vn.edu.epu.quanlyhoso.auth.entity.Account;
import vn.edu.epu.quanlyhoso.auth.entity.Role;
import vn.edu.epu.quanlyhoso.auth.repository.AccountRepository;
import vn.edu.epu.quanlyhoso.auth.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

    public String assignRole(String username, Integer newRoleId) {
        // 1. Tìm nhân viên cần thăng chức/giáng chức
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản: " + username));

        // 2. Lấy cái thẻ quyền hạn mới từ DB
        Role newRole = roleRepository.findById(newRoleId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy quyền này!"));

        // 3. Gắn thẻ mới cho nó và lưu lại
        account.setRole(newRole);
        accountRepository.save(account);

        return "Cấp quyền " + newRole.getRoleName() + " cho tài khoản " + username + " thành công!";
    }

        @Transactional
        public void deleteById(Integer id) {
        	accountRepository.deleteById(id);
        	System.out.print("du lieu da chạy toi day");
        }
        
//        
//        public List<AccountResponse> GetAllAccount(){
//        	List<AccountResponse> result = new ArrayList<AccountResponse>();
//        	List<Account> accounts = accountRepository.findAll();
//        	for(Account account : accounts) {
//        		String rolename = "No role";
//        		if(account.getRole() != null) {
//        			rolename = account.getRole().getRoleName();  
//        		}
//        		AccountResponse accountDto = new AccountResponse(account.getId(), account.getUsername(),rolename,account.getIsActive(),account.getEmail());
//        		result.add(accountDto);
//        		
//        	}
//        	return result; 
//        }
//        
//        // chức năng sửa tất cả của admin
//        public void updateAccount(Integer id, UpdateAccountRequest request) {
//            // 1. Tìm account, không thấy thì ném lỗi lập tức để Controller biết
//            Account account = accountRepository.findById(id)
//                    .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản có ID: " + id));
//            
//            // 2. Gán dữ liệu mới
//            account.setEmail(request.getEmail());
//            account.setIsActive(request.getIsActive());
//
//            // 3. Tìm và gán Role mới
//            if (request.getRoleId() != null) {
//                Role roleNew = roleRepository.findById(request.getRoleId())
//                        .orElseThrow(() -> new RuntimeException("Không tìm thấy quyền có ID: " + request.getRoleId()));
//                account.setRole(roleNew);
//            }
//
//            // 4. BẮT BUỘC PHẢI CÓ dòng này để lưu xuống DB
//            accountRepository.save(account);
//            System.out.println("Đã chạy lệnh SAVE xuống database thành công cho ID: " + id);
//        
//    }
}
