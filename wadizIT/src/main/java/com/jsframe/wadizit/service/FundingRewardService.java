package com.jsframe.wadizit.service;

import com.jsframe.wadizit.entity.Funding;
import com.jsframe.wadizit.entity.Reward;
import com.jsframe.wadizit.repository.FundingRewardRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log
@Service
public class FundingRewardService {

    @Autowired
    private FundingRewardRepository frRepo;

    public String create(Reward reward) {
        log.info("create()");
        String msg = null;
        log.info("" + reward);

        try {
            frRepo.save(reward);
            msg = "리워드 입력 성공";
        } catch (Exception e) {
            msg = "리워드 입력 실패";
            log.info(e.getMessage());
        }

        return msg;
    }

    public Iterable<Reward> read(Reward fundingNum) {
        log.info("read()");
        Funding fundingData = (fundingNum.getFundingNum());
        log.info("" + fundingData);
        Iterable<Reward> rewardList = frRepo.findByFundingNum(fundingData);

        return rewardList;
    }

    public String update(Reward reward, Long rewardNum) {
        log.info("update()");
        String msg = null;

        Reward rewardData = frRepo.findById(rewardNum).get();

        rewardData.setName(reward.getName());
        rewardData.setItem(reward.getItem());
        rewardData.setPrice(reward.getPrice());
        rewardData.setDelivery(rewardData.getDelivery());

        try {
            frRepo.save(rewardData);
            msg = "리워드 수정 성공";
        } catch (Exception e) {
            log.info(e.getMessage());
            msg = "리워드 수정 실패";

        }
        return msg;
    }


    public String delete(Long rewardNum) {
        log.info("delete()");
        String msg = null;

        try {
            frRepo.deleteById(rewardNum);
            msg = "리워드 삭제 성공";
        } catch (Exception e) {
            msg = "리워드 삭제 실패";
        }
        return msg;
    }
}


