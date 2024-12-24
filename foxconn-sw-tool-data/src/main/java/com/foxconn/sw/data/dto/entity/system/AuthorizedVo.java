package com.foxconn.sw.data.dto.entity.system;

import com.foxconn.sw.data.dto.enums.AuthorizedEnums;

public class AuthorizedVo {

    private AuthorizedEnums authorizedEnums;
    private boolean result;

    public AuthorizedEnums getAuthorizedEnums() {
        return authorizedEnums;
    }

    public void setAuthorizedEnums(AuthorizedEnums authorizedEnums) {
        this.authorizedEnums = authorizedEnums;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
