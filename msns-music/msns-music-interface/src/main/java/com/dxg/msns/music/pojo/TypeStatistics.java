package com.dxg.msns.music.pojo;

public class TypeStatistics {
    private String typeName;
    private Integer musicCount;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getMusicCount() {
        return musicCount;
    }

    public void setMusicCount(Integer musicCount) {
        this.musicCount = musicCount;
    }

    @Override
    public String toString() {
        return "TypeStatistics{" +
                "typeName='" + typeName + '\'' +
                ", musicCount=" + musicCount +
                '}';
    }
}
