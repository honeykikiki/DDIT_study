package kr.or.ddit.be.mapper;

import kr.or.ddit.be.vo.AttachFileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttachFileMapper {
    public long getAttachFileNo();

    public List<AttachFileVO> getFileAttachList(AttachFileVO attachFileVO);

    public int insertFileList(List<AttachFileVO> attachFileVOList);
}
