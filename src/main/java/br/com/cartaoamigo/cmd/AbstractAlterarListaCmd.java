package br.com.cartaoamigo.cmd;

import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import br.com.cartaoamigo.dao.base.BaseDao;

public abstract class AbstractAlterarListaCmd<E,TO, P> extends BaseDao{

	P p;

	public void alterarAll(List<TO> listaTelaTO, P pai) {
		p = pai;
		List<E> listaDoBanco = getListaBanco(pai);
		remover(listaDoBanco, listaTelaTO);
		adicionar(listaTelaTO, pai);
		atualizar(listaDoBanco, listaTelaTO, pai);

	}
	
	private void adicionar(List<TO> listaTelaTO, P pai) {
		List<TO> novos = listaTelaTO.stream().filter(registro -> Objects.isNull(getIdentificadorTO(registro))).collect(Collectors.toList());
		if (Objects.nonNull(novos)) {
			novos.forEach(novo -> cadastrar(novo, pai));
		}
	}
	
	private void remover(List<E> listaDoBanco, List<TO> listaTelaTO) {
		BiPredicate<TO, List<TO>> contemNaLista = (to, listaNova) -> listaNova.stream().anyMatch(novo -> Objects.nonNull(novo) && getIdentificadorTO(to).equals(getIdentificadorTO(novo)));
		listaDoBanco.removeIf(registro -> {
			if (!contemNaLista.test(getTO(registro), listaTelaTO)) {
				deletar(registro);
				return true;
			}
			return false;
		});

	}
	
	private void atualizar(List<E> listaDoBanco, List<TO> listaTelaTO, P pai) {
		BiPredicate<TO, List<TO>> contemNaLista = (to, listaNova) -> listaNova.stream().anyMatch(novo -> Objects.nonNull(novo) && getIdentificadorTO(to).equals(getIdentificadorTO(novo)));
		listaTelaTO.stream().filter(registro -> Objects.nonNull(getIdentificadorTO(registro))).forEach(registro -> {
			if (contemNaLista.test(registro, getTOListaBanco(listaDoBanco))) {
				cadastrar(registro,pai);
			}
		});
	}


	protected abstract TO getTO(E entity);
	protected abstract List<TO> getTOListaBanco(List<E> lista);
	protected abstract List<E> getListaBanco(P pai);
	protected abstract Long getIdentificadorTO(TO to);
	protected abstract void cadastrar(TO to, P p);
	protected abstract void deletar(E registro);


}
