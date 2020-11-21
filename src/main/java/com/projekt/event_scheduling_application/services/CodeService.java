package com.projekt.event_scheduling_application.services;

import com.projekt.event_scheduling_application.dao.Code;
import com.projekt.event_scheduling_application.exceptions.ESAException;
import com.projekt.event_scheduling_application.repositories.CodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
public class CodeService {
    private final CodeRepository codeRepository;

    public Code findCode(UUID pathCode) {
       return codeRepository.findById(pathCode).orElseThrow(() -> new ESAException(String
               .format("Oops! Link is not working")));
    }
    public String findUserName(UUID pathCode) {
        return findCode(pathCode).getUserName();
    }
    public String findEventName(UUID pathCode) {
        return findCode(pathCode).getEventName();
    }
}
