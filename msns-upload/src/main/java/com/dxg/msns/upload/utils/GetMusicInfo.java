package com.dxg.msns.upload.utils;

import com.dxg.msns.music.pojo.Music;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.flac.FlacFileReader;
import org.jaudiotagger.audio.mp3.MP3FileReader;
import org.jaudiotagger.audio.ogg.OggFileReader;
import org.jaudiotagger.audio.wav.WavFileReader;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.datatype.Artwork;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class GetMusicInfo {
    private static final List<String> MUSIC_TYPES = Arrays.asList("audio/mp3", "audio/flac", "audio/wav", "image/ogg");
    public Music getMusicTags(File file,String contentType){
        Music music = new Music();
        MP3FileReader mp3FileReader = new MP3FileReader();
        FlacFileReader flacFileReader = new FlacFileReader();
        WavFileReader wavFileReader = new WavFileReader();
        OggFileReader oggFileReader = new OggFileReader();

        AudioFile audioFile = null;
        try {
            //获取音乐信息
            if (contentType.contains(MUSIC_TYPES.get(0))) { //是music类型
                audioFile = mp3FileReader.read(file);
            } else if (contentType.contains(MUSIC_TYPES.get(1))) { //是flac类型
                audioFile = flacFileReader.read(file);
            } else if (contentType.contains(MUSIC_TYPES.get(2))) { //是wav类型
                audioFile = wavFileReader.read(file);
            } else if (contentType.contains(MUSIC_TYPES.get(3))) { //是ogg类型
                audioFile = oggFileReader.read(file);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TagException e) {
            e.printStackTrace();
        } catch (ReadOnlyFileException e) {
            e.printStackTrace();
        } catch (InvalidAudioFrameException e) {
            e.printStackTrace();
        } catch (CannotReadException e) {
            e.printStackTrace();
        }
        Tag tag = audioFile.getTag();
        //获取音乐名
        music.setMusicName(tag.getFirst(FieldKey.TITLE));
        //获取歌手名
        music.setMusicAuthor(tag.getFirst(FieldKey.ARTIST));
        //获取专辑
        music.setMusicAlbum(tag.getFirst(FieldKey.ALBUM));
        //获取时长
        int trackLength = audioFile.getAudioHeader().getTrackLength();
        int min = trackLength / 60;
        int second = trackLength % 60;
        music.setMusicLength(min + ":" + second);
        //获取发行年限
        music.setMusicPublisDate(tag.getFirst(FieldKey.YEAR));
        //获取图片
        Artwork firstArtwork = tag.getFirstArtwork();
        if (firstArtwork != null) {
            byte[] binaryData = firstArtwork.getBinaryData();
            String imageName = UUID.randomUUID().toString().replace("-", "") + ".jpg";
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream("D:\\msns\\images\\" + imageName);
                fos.write(binaryData);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String imgNetPath = "http://localhost:10000/file/images/" + imageName;
            music.setMusicCoverUrl(imgNetPath);
        }
        return music;
    }
}
