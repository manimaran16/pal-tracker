package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository
{

    private HashMap<Long,TimeEntry> timeEntryMap = new HashMap<>();
    private Long entryId = 0L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        entryId++;
        timeEntry.setId(entryId);
        timeEntryMap.put(entryId,timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        if(timeEntryMap.containsKey(timeEntryId)){
            return timeEntryMap.get(timeEntryId);
        }
        return null;
    }

    @Override
    public TimeEntry update(long timeEntryId, TimeEntry timeEntry) {

        if(timeEntryMap.containsKey(timeEntryId)){
            timeEntryMap.replace(timeEntryId, timeEntry);
            timeEntry.setId(timeEntryId);
            return timeEntry;
        }
        return null;
    }

    @Override
    public void delete(long timeEntryId) {
        if(timeEntryMap.containsKey(timeEntryId)){
            timeEntryMap.remove(timeEntryId);
        }
    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> entryList = timeEntryMap.values().stream().collect(Collectors.toList());
        return entryList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InMemoryTimeEntryRepository that = (InMemoryTimeEntryRepository) o;
        return timeEntryMap.equals(that.timeEntryMap) &&
                entryId.equals(that.entryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeEntryMap, entryId);
    }

    public HashMap<Long, TimeEntry> getTimeEntryMap() {
        return timeEntryMap;
    }

    public void setTimeEntryMap(HashMap<Long, TimeEntry> timeEntryMap) {
        this.timeEntryMap = timeEntryMap;
    }

    public Long getEntryId() {
        return entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }
}
