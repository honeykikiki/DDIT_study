package kr.or.ddit.be.util;

import kr.or.ddit.be.mapper.FilesMapper;
import kr.or.ddit.be.vo.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Controller
public class UploadFile {
    String saveDir = "/Users/heoseongjin/Documents/GitHub/ddit/ys/";

    @Autowired
    FilesMapper filesMapper;

    public String uploadFileToBinary(String dir, String file) {
        byte[] decodedFile = Base64.getDecoder().decode(file.split(",")[1]);
        uploadFolder(dir);

        String saveName = UUID.randomUUID().toString().replace("-", "");
        Path filePath = Paths.get(saveDir + dir + "/" + saveName);

        try {
            log.debug("filePath => {}", filePath);
            Files.write(filePath, decodedFile);
            log.info("파일 저장 완료: {}", filePath.toString());
        } catch (IOException e) {
            log.error("파일 저장 중 오류 발생", e);
        }

        return "/" + dir + "/" + saveName;
    }

    public List<FileVO> addFile(String dir, MultipartFile[] files, int id) {
        List<FileVO> fileVOList = new ArrayList<>();

        Arrays.stream(files).forEach((file) -> {
            String saveName = UUID.randomUUID().toString().replace("-", "") +"_" + file.getOriginalFilename();
            File path = new File(saveDir + dir + "/" + saveName);
            FileVO fileVO = new FileVO();
            uploadFolder(dir);

            try {
                file.transferTo(path);

                fileVO.setFileName("/" + dir +"/" + saveName);
                fileVO.setContent(file.getOriginalFilename());
                fileVO.setBoardId(id);
//                filesMapper.insert(fileVO);
                fileVOList.add(fileVO);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return fileVOList;
    }

    private void uploadFolder(String dir) {
        String newSaveDir = this.saveDir + dir;
        if (!new File(saveDir).isDirectory())
            new File(saveDir).mkdirs();
    }
}
