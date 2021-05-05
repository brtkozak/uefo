package com.example.payments

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BuyerRepository : JpaRepository<Buyer, Long>