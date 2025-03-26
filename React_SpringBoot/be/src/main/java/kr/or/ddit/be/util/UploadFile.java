package kr.or.ddit.be.util;

import kr.or.ddit.be.service.AttachFileService;
import kr.or.ddit.be.vo.AttachFileVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Controller
public class UploadFile {
    // svn 업로드 폴더 안에 추가
    String saveDir = "/Users/heoseongjin/Documents/GitHub/ddit/05_LAST/upload/";



    @Autowired
    AttachFileService attachFileService; // 미정

    // 바이너리 파일 저장
//    public String uploadFileToBinary(String dir, String file) {
//        byte[] decodedFile = Base64.getDecoder().decode(file.split(",")[1]);
//
//        String saveName = UUID.randomUUID().toString().replace("-", "");
//        Path filePath = Paths.get(saveDir + dir + "/" + saveName);
//
//        try {
//            log.debug("filePath => {}", filePath);
//            Files.write(filePath, decodedFile);
//            log.info("파일 저장 완료: {}", filePath.toString());
//        } catch (IOException e) {
//            log.error("파일 저장 중 오류 발생", e);
//        }
//
//        return "/" + dir + "/" + saveName;
//    }

    public List<AttachFileVO> addFiles(String dir, MultipartFile[] files, int id) {
        List<AttachFileVO> attachFileVOList = new ArrayList<>();
        long atchFileNo = attachFileService.getAttachFileNo();
        folderMkdirs(dir);

        for (int sn = 0; sn < files.length; sn++) {
            MultipartFile file = files[sn];

            String saveName = UUID.randomUUID().toString().replace("-", "") + "_" + file.getOriginalFilename();
            File path = new File(saveDir + dir + "/" + saveName);

            try {
                file.transferTo(path);

                AttachFileVO attachFileVO = new AttachFileVO();
                attachFileVO.setAtchFileNo(atchFileNo);
                attachFileVO.setFileSn(sn + 1);
                attachFileVO.setFileStrePath(dir + "/" + saveName);
                attachFileVO.setFileNm(file.getOriginalFilename());
                attachFileVO.setFileStreNm(saveName);
                attachFileVO.setFileSize(file.getSize());
                attachFileVO.setFileMime(file.getContentType());
                attachFileVO.setFileExtsn(
                        Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".") + 1)
                );
                attachFileVO.setFileViewSize(makeFancySize(file.getSize() + ""));

                attachFileVOList.add(attachFileVO);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // 디비에 파일 저장
        attachFileService.insertFileList(attachFileVOList);

        return attachFileVOList;
    }


    // 파일 삭제
    public void fileDelete(List<AttachFileVO> attachFileVOList) {
        attachFileVOList.stream().forEach((fileAttachVO) -> {
            Path filePath = Paths.get(saveDir + fileAttachVO.getFileStrePath());

            try {
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    // 파일 다운로드


    private void folderMkdirs(String dir) {
        String newSaveDir = this.saveDir + dir;
        if (!new File(newSaveDir).isDirectory()) {
            new File(newSaveDir).mkdirs();
        }
    }

    // fancySize 리턴("1059000")
    public String makeFancySize(String bytes) {
        log.info("bytes : " + bytes);
        String retFormat = "0";
        // 숫자형문자->실수형으로 형변환(1059000)
        double size = Double.parseDouble(bytes);// 1059000.0

        String[] s = {"bytes", "KB", "MB", "GB", "TB", "PB"};

        if (!bytes.equals("0")) {
            // bytes->KB
            // Math.log(1059000) : 13.8728
            // 13.8728 / 1024 :
            int idx = (int) Math.floor(Math.log(size) / Math.log(1024));// 1034
            DecimalFormat df = new DecimalFormat("#,###.##");
            double ret = ((size / Math.pow(1024, idx)));
            retFormat = df.format(ret) + " " + s[idx];// 1.01 MB
        } else {
            retFormat += " " + s[0];
        }

        return retFormat;
    }
}
