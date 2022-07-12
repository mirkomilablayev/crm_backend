package uz.crm.crmbackend.service.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.crm.crmbackend.repository.repositories.UserRepo;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;



}
