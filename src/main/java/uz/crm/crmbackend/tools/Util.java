package uz.crm.crmbackend.tools;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.crm.crmbackend.entity.EduCenter;
import uz.crm.crmbackend.entity.User;


@Component
public class Util {
    public Long getEduCenterId() {
        return (
                (User) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
        ).getEduCenter().getId();
    }

    public Long getCurrentUserId(){
        return (
                (User) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
        ).getId();
    }

    public User getCurrentUser(){
        return (
                (User) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
        );
    }


    public boolean checkBlank(String str) {
        return !str.trim().isEmpty();
    }

    private EduCenter getEduCenter(){
        return (
                (User) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
        ).getEduCenter();
    }
}
