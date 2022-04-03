package com.epam.songservice.dto;

public class SongIdsDto {
    private Integer [] ids;

    public SongIdsDto(Integer[] ids) {
        this.ids = ids;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}
