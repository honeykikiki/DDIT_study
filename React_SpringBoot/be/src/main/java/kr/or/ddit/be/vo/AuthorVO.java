package kr.or.ddit.be.vo;

import lombok.Data;

@Data
public class AuthorVO {
    public enum AuthType {
        WRITER, READ, UPDATE, DELETE
    }

    private Integer authorId;
    private String authorName;
    private AuthType authType;



    public void setAuthType(int type) {
        switch (type) {
            case 1 -> this.authType = AuthorVO.AuthType.WRITER;
            case 2 -> this.authType = AuthorVO.AuthType.READ;
            case 3 -> this.authType = AuthorVO.AuthType.UPDATE;
            case 4 -> this.authType = AuthorVO.AuthType.DELETE;
        }
    }


}
