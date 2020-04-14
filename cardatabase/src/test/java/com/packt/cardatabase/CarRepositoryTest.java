package com.packt.cardatabase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;
import com.packt.cardatabase.domain.Owner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CarRepository repository;

	// Test cases..
	@Test
	public void saveCar() {
		Owner owner1 = new Owner("John", "Johnson");
		entityManager.persistAndFlush(owner1);
		Car car = new Car("Tesla", "Model X", "White", "ABC-1234", 2017, 86000, owner1);
		entityManager.persistAndFlush(car);

		assertThat(car.getOwner()).isNotNull();
	}

	@Test
	public void deleteCars() {
		Owner owner1 = new Owner("John", "Johnson");
		Owner owner2 = new Owner("Mary", "Robinson");
		entityManager.persistAndFlush(owner1);
		entityManager.persistAndFlush(owner2);
		entityManager.persistAndFlush(new Car("Tesla", "Model X", "White", "ABC-1234", 2017, 86000, owner1));
		entityManager.persistAndFlush(new Car("Mini", "Cooper", "Yellow", "BWS-3007", 2015, 24500, owner2));

		repository.deleteAll();
		assertThat(repository.findAll()).isEmpty();
	}

}