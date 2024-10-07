import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import {Route, Routes} from "react-router-dom";
import {PATH} from "./utilities";
import SignIn from "./pages/SignIn/SignIn.tsx";
import SignUp from "./pages/SignUp/SignUp.tsx";


function App() {
    const queryClient = new QueryClient({
        defaultOptions: {
            queries: {
                retry: false,
                refetchOnWindowFocus: false,
            },
        },
    });

    return (
        <>
            <QueryClientProvider client={queryClient}>
                <Routes>
                    <Route path={PATH.SIGNIN} element={<SignIn/>}/>
                    <Route path={PATH.SIGNUP} element={<SignUp/>}/>
                    {/*<Suspense fallback={<Loading/>}>*/}
                    {/*</Suspense>*/}
                </Routes>
            </QueryClientProvider>
        </>
    );
}

export default App;