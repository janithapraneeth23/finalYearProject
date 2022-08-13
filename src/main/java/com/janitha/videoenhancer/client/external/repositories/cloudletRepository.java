package com.janitha.videoenhancer.client.external.repositories;

import com.janitha.videoenhancer.client.domain.mdbspringboot.model.CloudletInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.*;

import java.util.List;

public interface cloudletRepository extends MongoRepository<CloudletInfo, String>
{
    @Query("{name:'?0'}")
    CloudletInfo findItemByName(String name);

    @Query(value="{category:'?0'}", fields="{'name' : 1, 'ip' : 1}")
    List<CloudletInfo> findAll(String category);

    public long count();




}
