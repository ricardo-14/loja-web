<template>
    <div class="grid">
          <div class="col-12">
              <div class="card">
                  <Toast/>
                  <Toolbar class="mb-4">
                      <template v-slot:start>
                          <div class="my-2">
                              <Button label="Novo produto" icon="pi pi-plus" class="p-button-success mr-2" @click="novo()" />
                          </div>
                      </template>
                  </Toolbar>
  
                  <DataTable ref="dt" :value="produtos" v-model:selection="selectedProdutos" dataKey="id" :paginator="true" :rows="10" :filters="filters"
                              paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink "
                              currentPageReportTemplate="Showing {first} to {last} of {totalRecords} produtos" responsiveLayout="scroll">
                            <template #header>
                                <div class="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
                                    <h5 class="m-0">Produtos cadastrados</h5>
                                    <span class="block mt-2 md:mt-0 p-input-icon-left">
                                        <i class="pi pi-search" />
                                        <InputText v-model="filters['global'].value" placeholder="Search..." />
                                    </span>
                                </div>
                            </template>
  
                      <Column field="id" header="Id" :sortable="true" headerStyle="width:14%; min-width:10rem;"></Column>
                      <Column header="Imagem" headerStyle="width:14%; min-width:10rem;">
                        <template #body="slotProps">
                            <img :src="('images/bebidas/' + slotProps.data.imagem)" :alt="slotProps.data.nome" class="shadow-2" width="100" />
                        </template>
                      </Column>
                      <Column field="nome" header="Nome" :sortable="true" headerStyle="width:14%; min-width:10rem;"></Column>
                      <Column field="descricao" header="Descrição" :sortable="true" headerStyle="width:14%; min-width:10rem;"></Column>
                      <Column field="valor" header="Valor" :sortable="true" headerStyle="width:14%; min-width:10rem;"></Column>
                      <Column field="classificacao" header="Classificação" :sortable="true" headerStyle="width:14%; min-width:10rem;"></Column>
                      <Column field="marca.nome" header="Marca" :sortable="true" headerStyle="width:14%; min-width:10rem;"></Column>
                      <Column headerStyle="min-width:10rem;">
                          <template #body="{data}">
                              <Button icon="pi pi-image" class="p-button-rounded p-button-primary mr-2" @click="salvarImagem(data)" />
                              <Button icon="pi pi-pencil" class="p-button-rounded p-button-warning mr-2" @click="editar(data)" />
                              <Button icon="pi pi-trash" class="p-button-rounded p-button-danger mt-2" @click="confirmDeleteProduto(data)" />
                          </template>
                      </Column>
                    </DataTable>
                    <Dialog v-model:visible="deleteProdutoDialog" :style="{width: '450px'}" header="Confirmação" :modal="true">
                        <div class="flex align-items-center justify-content-center">
                            <i class="pi pi-exclamation-triangle mr-3" style="font-size: 2rem" />
                            <span v-if="produtos">Deseja realmente excluir o produto <b>{{produto.nome}}</b>?</span>
                        </div>
                        <template #footer>
                            <Button label="Não" icon="pi pi-times" class="p-button-text" @click="deleteProdutoDialog = false"/>
                            <Button label="Sim" icon="pi pi-check" class="p-button-text" @click="excluir(produto)" />
                        </template>
                    </Dialog>
              </div>
          </div>
      </div>
  
</template>

<script>
import {FilterMatchMode} from 'primevue/api';
import axios from 'axios'

export default {
	data() {
		return {
            produtos: [],
			filters: {},
            deleteProdutoDialog: false,
		}
	},
	created() {
		this.initFilters();
	},
	methods: {
        load() {
            axios
            .get('http://localhost:8080/produto')
            .then(resp => {
                this.produtos = resp.data
            })
        },
		editar(produto) {
            const id = produto.id
            this.$router.push(`/produto-form/${id}`)
        },
        salvarImagem(produto) {
            const id = produto.id
            this.$router.push(`/produto-imagem/${id}`)
        },
        novo() {
            this.$router.push('/produto-form/')
        },
		confirmDeleteProduto(produto) {
			this.produto = produto;
			this.deleteProdutoDialog = true;
		},
        excluir(produto) {
            const id = produto.id
            axios
                .delete(`http://localhost:8080/produto/${id}`)
                .then(this.load(),
				location.reload())
                .catch(error => alert(error))
                this.deleteProdutoDialog = false;
                
        },
		initFilters() {
            this.filters = {
                'global': {value: null, matchMode: FilterMatchMode.CONTAINS},
            }
        }
	},
	mounted() {
		this.load();
	}
}
</script>

<style scoped lang="scss">
@import '../assets/demo/badges.scss';
</style>