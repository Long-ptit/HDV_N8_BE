package com.example.android.repository;

import com.example.android.model.Manufacturer;
import com.example.android.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {



}
