package domain.models;

import java.util.List;
import java.util.Optional;

import domain.models.decks.RepositoryException;

public interface IRepository<T> {
	public Optional<T> findById(Long id) throws RepositoryException;
	
	public List<T> findAll() throws RepositoryException;

	public void save(T t) throws RepositoryException;

	public void deleteById(Long id) throws RepositoryException;
}
