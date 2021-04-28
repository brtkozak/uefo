package com.example.payments.data

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PropertyRepository : JpaRepository<Property, Long>