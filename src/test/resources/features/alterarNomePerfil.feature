Feature: Alterar Nome

  Scenario:Alterar nome do perfil na Eveclass
    Given que acesso o site Testando Eveclass
    When clico no botao Começar Agora
    When clico no botao Entrar
    When insiro o Email
    When insiro a Senha
    When clico no botao Entrar2
    When clico no icone do Usuario
    When clico no nome do Usuario
    When altero o nome do Usuario
    When clico no botao Salvar
    Then exibe o pop up de confirmaçao