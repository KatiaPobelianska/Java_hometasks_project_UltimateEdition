package spring.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.study.dao.LogEntryDao;
import spring.study.model.LogEntry;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class SchoolJournalController {

    private LogEntryDao logEntryDao;

    @Autowired
    public SchoolJournalController(LogEntryDao logEntryDao) {
        this.logEntryDao = logEntryDao;
    }
    @GetMapping
    public String getJournal() {
        return "Welcome to the school journal!";
    }
    @PostMapping("/record")
    public void createRecord(@RequestBody LogEntry logEntry) {
        logEntryDao.save(logEntry);
    }

    @PutMapping("/record/{id}")
    public void updateRecord(@PathVariable long id, @RequestBody LogEntry logEntry) {
        logEntry.setId(id);
        logEntryDao.update(logEntry, id);
    }

    @DeleteMapping("/record/{id}")
    public void deleteRecord(@PathVariable long id) {
        logEntryDao.delete(id);
    }

    @GetMapping("/records")
    public List<LogEntry> getAllRecords() {
        return logEntryDao.getAll();
    }
    @GetMapping("record/{id}")
    public LogEntry getRecordById(@PathVariable long id){
        return logEntryDao.getById(id);
    }

    @GetMapping("/records/class")
    public List<LogEntry> getRecordsByClassName(@RequestParam String className) {
        return logEntryDao.getAllByClass(className);
    }

    @GetMapping("/record")
    public LogEntry getRecordByClassAndFullName(@RequestParam String className, @RequestParam String fullName) {
        LogEntry logEntry = logEntryDao.getByClassAndFullname(className, fullName);
        if (logEntry == null){
            return  null;
        }
        return logEntry;
    }

}
