package aleksey.stepanyuk.domain.repo;

import aleksey.stepanyuk.domain.entity.ProductProposal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductProposalRepository extends JpaRepository<ProductProposal, Long> {
}