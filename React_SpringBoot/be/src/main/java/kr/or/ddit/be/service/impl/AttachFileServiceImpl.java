package kr.or.ddit.be.service.impl;

import kr.or.ddit.be.mapper.AttachFileMapper;
import kr.or.ddit.be.service.AttachFileService;
import kr.or.ddit.be.vo.AttachFileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachFileServiceImpl implements AttachFileService {

    @Autowired
    private AttachFileMapper attachFileMapper;

    @Override
    public long getAttachFileNo() {
        return attachFileMapper.getAttachFileNo();
    }

    @Override
    public List<AttachFileVO> getFileAttachList(AttachFileVO attachFileVO) {
        return attachFileMapper.getFileAttachList(attachFileVO);
    }

    @Override
    public int insertFileList(List<AttachFileVO> attachFileVOList) {
        return attachFileMapper.insertFileList(attachFileVOList);
    }
}
