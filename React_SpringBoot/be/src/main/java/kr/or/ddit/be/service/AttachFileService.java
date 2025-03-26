package kr.or.ddit.be.service;

import kr.or.ddit.be.vo.AttachFileVO;

import java.util.List;

public interface AttachFileService {
    public long getAttachFileNo();

    public List<AttachFileVO> getFileAttachList(AttachFileVO attachFileVO);

    public int insertFileList(List<AttachFileVO> attachFileVOList);
}
