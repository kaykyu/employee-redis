package kq.practice.ssf19.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import kq.practice.ssf19.model.Employee;

@Repository
public class EmployeeRepo {

    private String hashRef = "employees";

    @Resource(name="redisEmployeeTemplate")
    private HashOperations<String, String, Employee> hOps;

    public void saveRecord(Employee employee) {
        hOps.put(hashRef, employee.getId().toString(), employee);
     }

    public Employee getRecord(Integer id) {
         return hOps.get(hashRef, String.valueOf(id));
     }
    
     public List<Employee> getAll() {
        List<Employee> list = new ArrayList<>();
        for (String keys : hOps.keys(hashRef)) {
            list.add(hOps.get(hashRef, keys));
        }

        return list;
     }
}
