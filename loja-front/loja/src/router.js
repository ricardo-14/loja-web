import { createRouter, createWebHashHistory } from 'vue-router';
import App from './App.vue';

const routes = [
    {
        path: '/',
        name: 'app',
        component: App,
        children: [
            {
                path: '',
                name: 'dashboard',
                component: () => import('./components/Dashboard.vue')
            },
            {
                path: '/marca',
                name: 'marca',
                component: () => import('./views/MarcaView.vue')
            },
            {
                path: '/marca-form/:id',
                name: 'marca-form',
                component: () => import('./views/MarcaFormView.vue')
              }, 
              {
                path: '/marca-form',
                name: 'marca-form-new',
                component: () => import('./views/MarcaFormView.vue')
              },
              {
                  path: '/produto',
                  name: 'produto',
                  component: () => import('./views/ProdutoView.vue')
              },
              {
                path: '/produto-lista',
                name: 'produto-lista',
                component: () => import('./views/ProdutoListaView.vue')
            },
              {
                  path: '/produto-form/:id',
                  name: 'produto-form',
                  component: () => import('./views/ProdutoFormView.vue')
                },
                {
                    path: '/produto-imagem/:id',
                    name: 'produto-imagem',
                    component: () => import('./views/ProdutoImagem.vue')
                  }, 
                {
                  path: '/produto-form',
                  name: 'produto-form-new',
                  component: () => import('./views/ProdutoFormView.vue')
                },
                {
                  path: '/carrinho',
                  name: 'carrinho',
                  component: () => import('./views/CarrinhoView.vue')
              },
        ]
    }
];

const router = createRouter({
    history: createWebHashHistory(),
    routes,
});

export default router;
