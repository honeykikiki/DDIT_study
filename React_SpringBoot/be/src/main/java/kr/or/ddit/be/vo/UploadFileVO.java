package kr.or.ddit.be.vo;

import lombok.Data;

import java.nio.file.Path;

@Data
public class UploadFileVO {
    // 바이너리 용
    byte[] decodeFile;
    Path filePath;


    // multiPart 용


    String fileName;

}
