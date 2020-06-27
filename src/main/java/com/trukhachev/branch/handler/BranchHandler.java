package com.trukhachev.branch.handler;

import com.trukhachev.branch.dto.ResponseDTO;
import com.trukhachev.branch.persistence.entity.BranchEntity;
import com.trukhachev.branch.persistence.repository.BrunchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import javax.crypto.Mac;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BranchHandler {

    private final BrunchRepository brunchRepository;

    @Transactional
    public ResponseDTO getBranch(final String id)  {

        Optional<BranchEntity> branchEntityOptional = brunchRepository.findById(Long.parseLong(id));

        if (branchEntityOptional.isPresent()) {
            var branchEntity = branchEntityOptional.get();

            return new ResponseDTO().
                    setId(branchEntity.getId()).
                    setTitle(branchEntity.getTitle()).
                    setAddress(branchEntity.getAddress()).
                    setLat(branchEntity.getLat()).
                    setLon(branchEntity.getLon());
        }

        return null;
    }


    public ResponseDTO getNearest(final String lat, final String lon) {

        Iterable<BranchEntity> all = brunchRepository.findAll();

        BranchEntity nearest = null;
        double minDist = 0;

        for(var branch: all) {

            var dist = getDist(lat, lon, branch.getLat(), branch.getLon());

            if (nearest == null) {
                nearest = branch;
                minDist = dist;
            } else {
                if (dist < minDist) {
                    nearest = branch;
                    minDist = dist;
                }
            }
        }

        if (nearest == null) return null;

        return new ResponseDTO().
                setId(nearest.getId()).
                setLon(nearest.getLon()).
                setLat(nearest.getLat()).
                setTitle(nearest.getTitle()).
                setAddress(nearest.getAddress()).
                setDistance((int)minDist);
    }

    private double getDist(final String ax, final String ay, double x2, double y2) {

        double x1 = Math.toRadians(Double.parseDouble(ax));
        double y1 = Math.toRadians(Double.parseDouble(ay));
        x2 = Math.toRadians(x2);
        y2 = Math.toRadians(y2);

        var result = 2 * 6371 * Math.asin(Math.sqrt(Math.pow(Math.sin((Math.abs(x2 - x1))/2), 2) + Math.cos(x2) * Math.cos(x1) * Math.pow(Math.sin((Math.abs(y2 - y1))/2), 2)));
        return Math.round (result * 1000);
    }

}
