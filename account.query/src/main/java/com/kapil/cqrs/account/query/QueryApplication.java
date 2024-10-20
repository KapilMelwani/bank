package com.kapil.cqrs.account.query;

import com.kapil.cqrs.account.query.api.queries.FindAccountByAccountHolderQuery;
import com.kapil.cqrs.account.query.api.queries.FindAccountByIdQuery;
import com.kapil.cqrs.account.query.api.queries.FindAccountWithBalanceQuery;
import com.kapil.cqrs.account.query.api.queries.FindAllAccountsQuery;
import com.kapil.cqrs.account.query.api.queries.QueryHandler;
import com.kapil.cqrs.core.infrastructure.QueryDispatcher;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QueryApplication {

	@Autowired
	private QueryDispatcher queryDispatcher;

	@Autowired
	private QueryHandler queryHandler;


	@PostConstruct
	public void registerQueries() {
		queryDispatcher.registerHandler(FindAllAccountsQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountByIdQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountByAccountHolderQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountWithBalanceQuery.class, queryHandler::handle);
	}

	public static void main(String[] args) {
		SpringApplication.run(QueryApplication.class, args);
	}

}
