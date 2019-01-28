package tk.raficruz.srm.model.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * @param <E> entity
 * @param <D> dto
 */
public abstract class BaseMapper<E, D> {

  public PageImpl<D> toPage(Page<E> pages, Pageable page) {
    List<D> result = new ArrayList<>();

    for (E entity : pages) {
      result.add(toDTO(entity));
    }

    return new PageImpl<>(result, page, pages.getTotalElements());
  }

  public List<D> toListDTO(List<E> list) {
    List<D> result = new ArrayList<>();

    if (list == null) {
      return result;
    }

    return list.stream().map(this::toDTO).collect(Collectors.toList());
  }

  public Set<D> toSetListDTO(Set<E> setList) {
    Set<D> result = new HashSet<>();

    if (setList == null) {
      return result;
    }

    for (E entity : setList) {
      result.add(toDTO(entity));
    }

    return result;
  }

  public List<E> toListEntity(List<D> list) {
    List<E> result = new ArrayList<>();

    if (list == null) {
      return result;
    }

    for (D dto : list) {
      if (dto != null) {
        result.add(toEntity(dto));
      }
    }

    return result;
  }

  public Set<E> toSetEntity(Set<D> list) {
    Set<E> result = new HashSet<>();

    if (list == null) {
      return result;
    }

    for (D dto : list) {
      if (dto != null) {
        result.add(toEntity(dto));
      }
    }

    return result;
  }

  public Set<E> listDTOtoSetEntity(List<D> list) {
    Set<E> result = new HashSet<>();

    if (list == null) {
      return result;
    }

    for (D dto : list) {
      if (dto != null) {
        result.add(toEntity(dto));
      }
    }

    return result;
  }

  public List<D> setEntityToListDTO(Set<E> setList) {
    List<D> result = new ArrayList<>();

    if (setList == null) {
      return result;
    }

    for (E entity : setList) {
      result.add(toDTO(entity));
    }

    return result;
  }

  public abstract D toDTO(E entity);

  public abstract E toEntity(D dto);
}
