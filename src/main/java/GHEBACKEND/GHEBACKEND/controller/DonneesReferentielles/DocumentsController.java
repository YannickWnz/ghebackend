package GHEBACKEND.GHEBACKEND.controller.DonneesReferentielles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class DocumentsController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/api/upload")
    public ResponseEntity<String> uploadDoc(
        // @RequestParam("file") MultipartFile file,
        // @RequestParam("fileType") String fileType
        @RequestParam("fileCount") String fileCount,
        @RequestParam Map<String, String> params,
        @RequestParam Map<String, MultipartFile> files
    ) {

        try {
            for (int i = 0; i < Integer.parseInt(fileCount); i++) {
                String fileKey = "file_" + i;
                String fileTypeKey = "fileType_" + i;

                MultipartFile file = files.get(fileKey);
                String fileType = params.get(fileTypeKey);

                if (file != null) {
                    // Save file to the local directory
                    Path filePath = Paths.get(uploadDir + file.getOriginalFilename());
                    Files.write(filePath, file.getBytes());

                    // Log or process the file and its type
                    System.out.println("File: " + file.getOriginalFilename());
                    System.out.println("Type: " + fileType);

                    // TODO: Save file and its metadata (type, path, etc.) to the database
                }
            }

            return ResponseEntity.ok("All files uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
}
}