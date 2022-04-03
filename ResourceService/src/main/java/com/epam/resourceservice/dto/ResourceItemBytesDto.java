package com.epam.resourceservice.dto;

public class ResourceItemBytesDto {
    private byte[] audioBytes;

    public ResourceItemBytesDto(byte[] audioBytes) {
        this.audioBytes = audioBytes;
    }

    public byte[] getAudioBytes() {
        return audioBytes;
    }

    public void setAudioBytes(byte[] audioBytes) {
        this.audioBytes = audioBytes;
    }
}
