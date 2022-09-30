package com.lguplus.fleta.domain.repository;

import com.lguplus.fleta.data.dto.HdtvAdvertisementInfoLogDto;
import com.lguplus.fleta.data.dto.HdtvAdvertisementMasterLogDto;
import com.lguplus.fleta.data.dto.UxHotvodHitcountLogDto;
import com.lguplus.fleta.data.entity.*;
import com.lguplus.fleta.provider.jpa.*;
import com.lguplus.fleta.repository.log.CtcLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
@AllArgsConstructor
public class CtcLogRepositoryImpl implements CtcLogRepository {

    private final UxHotvodCategoryJpaRepository uxHotvodCategoryJpaRepository;
    private final UxHotvodCategoryContentJpaRepository uxHotvodCategoryContentJpaRepository;
    private final UxHotvodContentJpaRepository uxHotvodContentJpaRepository;
    private final UxHotvodContentExtensionJpaRepository uxHotvodContentExtensionJpaRepository;
    private final UxHotvodHitcountLogJpaEmRepository uxHotvodHitcountLogJpaEmRepository;
    private final UxHotvodSiteJpaRepository uxHotvodSiteJpaRepository;
    private final HdtvAdvertisementInfoJpaRepository hdtvAdvertisementInfoJpaRepository;
    private final HdtvAdvertisementInfoLogJpaEmRepository hdtvAdvertisementInfoLogJpaEmRepository;
    private final HdtvAdvertisementMasterJpaRepository hdtvAdvertisementMasterJpaRepository;
    private final HdtvAdvertisementMasterLogJpaEmRepository hdtvAdvertisementMasterLogJpaEmRepository;

    @Override
    public List<UxHotvodCategory> findAllUxHotvodCategory() {

        return uxHotvodCategoryJpaRepository.findAll();
    }

    @Override
    public List<UxHotvodCategoryContent> findAllUxHotvodCategoryContent() {

        return uxHotvodCategoryContentJpaRepository.findAll();
    }

    @Override
    public List<UxHotvodContent> findAllUxHotvodContent() {

        return uxHotvodContentJpaRepository.findAll();
    }

    @Override
    public List<UxHotvodContentExtension> findAllUxHotvodContentExtension() {

        return uxHotvodContentExtensionJpaRepository.findAll();
    }

    @Override
    public Stream<UxHotvodHitcountLogDto> streamAllUxHotvodHitcountLog() {

        return uxHotvodHitcountLogJpaEmRepository.streamAll();
    }

    @Override
    public List<UxHotvodSite> findAllUxHotvodSite() {

        return uxHotvodSiteJpaRepository.findAll();
    }

    @Override
    public List<HdtvAdvertisementInfo> findAllHdtvAdvertisementInfo() {

        return hdtvAdvertisementInfoJpaRepository.findAll();
    }

    @Override
    public Stream<HdtvAdvertisementInfoLogDto> streamAllHdtvAdvertisementInfoLog() {

        return hdtvAdvertisementInfoLogJpaEmRepository.streamAll();
    }

    @Override
    public List<HdtvAdvertisementMaster> findAllHdtvAdvertisementMaster() {

        return hdtvAdvertisementMasterJpaRepository.findAll();
    }

    @Override
    public Stream<HdtvAdvertisementMasterLogDto> streamAllHdtvAdvertisementMasterLog() {

        return hdtvAdvertisementMasterLogJpaEmRepository.streamAll();
    }
}
