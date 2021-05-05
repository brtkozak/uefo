package com.example.payments.repository

import com.example.payments.dto.Property
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PropertyRepository : JpaRepository<Property, Long>