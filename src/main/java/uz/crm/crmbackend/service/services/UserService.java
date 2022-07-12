package uz.crm.crmbackend.service.services;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.crm.crmbackend.entity.File;
import uz.crm.crmbackend.entity.User;
import uz.crm.crmbackend.repository.repositories.FileRepo;
import uz.crm.crmbackend.repository.repositories.UserRepo;
import uz.crm.crmbackend.tools.Constant;
import uz.crm.crmbackend.tools.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final FileRepo fileRepo;
    private final Util util;

    public HttpEntity<?> saveFile(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {
            File newFile = new File();
            newFile.setContent_type(file.getContentType());
            String originalFilename = file.getOriginalFilename();
            newFile.setOriginalName(originalFilename);
            assert originalFilename != null;
            String[] split = originalFilename.split("\\.");
            String generatedName = UUID.randomUUID() + "." + split[split.length - 1];
            String filePath = Constant.filePaths + "/" + generatedName;
            newFile.setGeneratedName(generatedName);
            newFile.setFile_path(filePath);
            File save = fileRepo.save(newFile);
            Path path = Paths.get(filePath);
            try {
                Files.copy(file.getInputStream(), path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            User currentUser = util.getCurrentUser();
            return ResponseEntity.status(HttpStatus.OK).body(save.getId());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong");
    }
}
