package spring.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.ref.PhantomReference;

/*Напишите RESTful web-приложение для создания, изменения,
удаления и просмотра записей школьного журнала (класс, ФИО ученика, предметы
и соответствующие им оценки по датам получения). В приложении должен присутствовать
слой persistence с DAO. Просмотр оценок можно делать по всем ученикам класса,
передав название класса,
или по отдельному ученику (передаётся имя класса, и ФИО ученика).*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogEntry {
    private long id;
    private String className;
    private String fullName;
    private String subject;
    private int mark;

}
