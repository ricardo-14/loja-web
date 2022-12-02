<template>
    <div class="grid">
          <div class="col-12">
              <div class="card">
                  <Toast/>
                  <Toolbar class="mb-4">
                      <template v-slot:start>
                          <div class="my-2">
                              <Button label="New" icon="pi pi-plus" class="p-button-success mr-2" @click="novo()" />
                          </div>
                      </template>
                  </Toolbar>
  
                  <DataTable ref="dt" :value="produtos" v-model:selection="selectedProdutos" dataKey="id" :paginator="true" :rows="10" :filters="filters"
                              paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown" :rowsPerPageOptions="[5,10,25]"
                              currentPageReportTemplate="Showing {first} to {last} of {totalRecords} produtos" responsiveLayout="scroll">
                      <template #header>
                          <div class="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
                              <h5 class="m-0">Lista de produtos</h5>
                              <span class="block mt-2 md:mt-0 p-input-icon-left">
                                  <i class="pi pi-search" />
                                  <InputText v-model="filters['global'].value" placeholder="Search..." />
                              </span>
                          </div>
                      </template>
  
                      <Column field="id" header="Id" :sortable="true" headerStyle="width:14%; min-width:10rem;"></Column>
                      <Column field="nome" header="Nome" :sortable="true" headerStyle="width:14%; min-width:10rem;"></Column>
                      <Column field="valor" header="Valor" :sortable="true" headerStyle="width:14%; min-width:10rem;"></Column>
                      <Column field="marca.nome" header="Marca" :sortable="true" headerStyle="width:14%; min-width:10rem;"></Column>
                      <Column headerStyle="min-width:10rem;">
                          <template #body="{data}">
                              <Button icon="pi pi-image" class="p-button-rounded p-button-primary mt-2" @click="editarImagem(data)" /> 
                              <Button icon="pi pi-pencil" class="p-button-rounded p-button-warning mr-2" @click="editar(data)" />
                              <Button icon="pi pi-trash" class="p-button-rounded p-button-danger mt-2" @click="excluir(data)" />
                          </template>
                      </Column>
                  </DataTable>
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
        editarImagem(produto) {
            const id = produto.id
            this.$router.push(`/produto-imagem/${id}`)
        },
        novo() {
            this.$router.push('/produto-form/')
        },
        excluir(produto) {
            const id = produto.id
            axios
                .delete(`http://localhost:8080/produto/${id}`)
                .then(this.load(),
				location.reload())
                .catch(error => alert(error))
                
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