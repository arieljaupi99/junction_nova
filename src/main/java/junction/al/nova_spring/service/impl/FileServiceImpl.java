package junction.al.nova_spring.service.impl;

import junction.al.nova_spring.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class FileServiceImpl implements FileService {
    @Value("${file.upload.directory}")
    private String uploadDirectory;
    private final String USER_HOME = System.getProperty("user.home");

    @Override
    public String saveAndReturnPath(String roomId, String base64Contract, String type) {

        try {
            String subfolderPath = Paths.get(USER_HOME, uploadDirectory).toString();
            File subfolder = new File(subfolderPath);
            if (!subfolder.exists()) {
                subfolder.mkdir();
            }
            String fileName = "contract_" + roomId + "." + type;

            //Define the file path inside the subfolder
            String filePath = Paths.get(subfolderPath, fileName).toString();
            File contractFile = new File(filePath);
            if (contractFile.exists()) {
                contractFile.delete();
            }
            byte[] fileBytes = Base64.getDecoder().decode(base64Contract);

            // Save the file
            Path path = Paths.get(filePath);
            Files.write(path, fileBytes);

            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
