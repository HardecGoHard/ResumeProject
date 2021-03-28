package org.BaseJava.Model;

import java.io.Serializable;

public class Link implements Serializable {
    private String name;
    private String url;

    public Link(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return  name +": "+ url;
    }
}
