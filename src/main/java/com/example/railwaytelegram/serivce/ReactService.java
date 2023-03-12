package com.example.railwaytelegram.serivce;

import com.example.railwaytelegram.dto.InfoDto;
import com.example.railwaytelegram.dto.OperationDto;
import com.example.railwaytelegram.dto.OperationHistoryDto;
import com.example.railwaytelegram.entity.UserOperationEntity;
import com.example.railwaytelegram.repository.UserEntityRepository;
import com.example.railwaytelegram.repository.UserOperationEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReactService {
    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private UserOperationEntityRepository userOperationEntityRepository;

    public InfoDto getInfo(Long userId) {
        BigDecimal totalSum = userOperationEntityRepository.getTotalSum(userId);
        BigDecimal inSum = userOperationEntityRepository.getInSum(userId);
        BigDecimal outSum = userOperationEntityRepository.getOutSum(userId);
        return new InfoDto(totalSum, inSum, outSum);
    }

    public void saveOperation(OperationDto dto) {
        UserOperationEntity entity = new UserOperationEntity();
        entity.setUser(userEntityRepository.findById(dto.getUserId()).orElseThrow(EntityNotFoundException::new));
        entity.setSum(dto.getSum());
        entity.setCategory(dto.getCategory());
        entity.setCreatedAt(LocalDateTime.now());
        userOperationEntityRepository.saveAndFlush(entity);
    }

    public List<OperationHistoryDto> getOperationHistory(Long userId) {
        List<UserOperationEntity> operations = userOperationEntityRepository.findTop5ByUserId(userId);
        return operations.stream().map(OperationHistoryDto::new).toList();
    }
}
