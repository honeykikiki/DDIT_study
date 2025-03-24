package kr.or.ddit.be.vo;

import lombok.Data;

import java.util.List;

@Data
public class EmplVO {
    private long emplNo;
    private String emplName;
    private String emplAddr;
    private String emplTel;
    private String emplEmail;
    private String emplPassword;

    private List<AuthorVO.AuthType> authList; // 0 쓰기 1 읽기 2 수정 3 삭제

    public void setAuthType(int type) {
        switch (type) {
            case 1 -> authList.add(AuthorVO.AuthType.WRITER);
            case 2 -> authList.add(AuthorVO.AuthType.READ);
            case 3 -> authList.add(AuthorVO.AuthType.UPDATE);
            case 4 -> authList.add(AuthorVO.AuthType.DELETE);
        }
    }

    public boolean validWrite() {
        return authList.contains(AuthorVO.AuthType.WRITER);
    }
}

