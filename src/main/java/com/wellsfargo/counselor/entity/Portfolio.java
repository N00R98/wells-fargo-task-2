package com.wellsfargo.counselor.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long portfolioId;

    @Column(nullable = false)
    private String name;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Security> securities = new ArrayList<>();

    protected Portfolio() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Portfolio(String name, Client client) {
        this.name = name;
        this.client = client;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getPortfolioId() { return portfolioId; }

    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Client getClient() { return client; }
    public void setClient(Client client) {
        this.client = client;
        this.updatedAt = LocalDateTime.now();
    }

    public List<Security> getSecurities() { return securities; }
    public void setSecurities(List<Security> securities) { this.securities = securities; }

    public void addSecurity(Security security) {
        securities.add(security);
        security.setPortfolio(this);
        this.updatedAt = LocalDateTime.now();
    }

    public void removeSecurity(Security security) {
        securities.remove(security);
        security.setPortfolio(null);
        this.updatedAt = LocalDateTime.now();
    }
}
