package com.canary.biz.acl.infra;

import lombok.Data;

import java.io.Serializable;

@Data
public class JSTreeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String parent;

    private String text;

    private String icon;

    private State state;

    @Data
    public class State {

        private boolean opened;

        private boolean selected;

        private boolean disabled;

    }

}
