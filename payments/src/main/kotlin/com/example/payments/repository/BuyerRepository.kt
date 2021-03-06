package com.example.payments.repository

import com.example.payments.entity.Buyer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BuyerRepository : JpaRepository<Buyer, Long>