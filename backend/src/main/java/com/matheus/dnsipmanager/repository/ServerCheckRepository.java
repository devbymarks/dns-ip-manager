package com.matheus.dnsipmanager.repository;
import com.matheus.dnsipmanager.model.ServerCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ServerCheckRepository extends JpaRepository<ServerCheck,Long>{
    List<ServerCheck> findTop20ByOrderByCheckedAtDesc();
}
