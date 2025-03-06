package com.example.calendarapp;

import com.example.calendarapp.event.*;
import com.example.calendarapp.reader.EventCsvReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class CalendarAppApplication {

    public static void main(String[] args) throws IOException, CsvException {
        Schedule schedule = new Schedule();

        // 개별 추가
/*        HashSet<String> participants = new HashSet<String>();
        participants.add("paul.jo");

        Meeting meeting1 = new Meeting(
                1, "meeting1",
                ZonedDateTime.now(), ZonedDateTime.now().plusHours(1),
                participants, "meetingRoomA", "스터디"
        );

        schedule.add(meeting1);

        Todo todo1 = new Todo(
                2, "todo1",
                ZonedDateTime.now().plusHours(3), ZonedDateTime.now().plusHours(4),
                "할일 적기"
        );

        schedule.add(todo1);

        Todo todo2 = new Todo(
                3, "todo2",
                ZonedDateTime.now().plusHours(5), ZonedDateTime.now().plusHours(4),
                "할일 적기"
        );

        schedule.add(todo1);*/

        // csv 파일로 추가
        EventCsvReader csvReader = new EventCsvReader();
        String meetingCsvPath = "/data/meeting.csv";

        List<Meeting> meetings = csvReader.readMeetings(meetingCsvPath);
        meetings.forEach(schedule::add);

        schedule.printAll();

    }

}
