package com.example.calendarapp.reader;

import com.example.calendarapp.event.Meeting;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class EventCsvReader {
    private final RawCsvReader rawCsvReader;

    public EventCsvReader(RawCsvReader rawCsvReader){
        this.rawCsvReader = rawCsvReader;
    }

    public List<Meeting> readMeetings(String path) throws IOException, CsvException {
        List<Meeting> result = new ArrayList<>();

        // 데이터를 읽는 부분
        List<String[]> read = rawCsvReader.readAll(path);
        for(int i = 0; i < read.size(); i++){
            if(skipHeader(i)){
                continue;
            }

            String[] each = read.get(i);

            // Meeting으로 변환 부분
            result.add(
                    new Meeting(
                            Integer.parseInt(each[0]),
                            each[2],
                            ZonedDateTime.of(
                                    LocalDateTime.parse(
                                            each[6],
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                    ),
                                    ZoneId.of("Asia/Seoul")
                            ),
                            ZonedDateTime.of(
                                    LocalDateTime.parse(
                                            each[7],
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                    ),
                                    ZoneId.of("Asia/Seoul")
                            ),
                            new HashSet<>(Arrays.asList(each[3].split(","))),
                            each[4],
                            each[5]
                    )
            );
        }




        return result;
    }

    private static boolean skipHeader(int i) {
        return i == 0;
    }

}
