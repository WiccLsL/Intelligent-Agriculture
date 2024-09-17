package com.example.smartagriculture.repository;

import com.example.smartagriculture.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropRepository extends JpaRepository<Crop, Long> {
}
