package br.com.users.manager.repository;

import br.com.users.manager.domain.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository <Profile, Long>{
}
