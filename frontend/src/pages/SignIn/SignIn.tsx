import {Button, Form, Input, message} from "antd";
import {Link} from "react-router-dom";
import {IUser} from "../../utilities";
import {useForm} from "antd/es/form/Form";

const SignIn = () => {
    // const navigate = useNavigate();
    const [form] = useForm();
    // const mutationSignin = useMutation({
    //     mutationFn: (data: IUser) => {
    //         return apiSignin(data)
    //     }
    // });
    const handleSubmit = async (value: IUser) => {
        // mutationSignin.mutate(data, {
        //     onSuccess: (data) => {
        //         message.success('Bạn đã đăng nhập thành công')
        //         navigate("/");
        //         console.log("user", data);
        //
        //     },
        //     onError: (error) => {
        //         console.log(error);
        //         message.error('Tài khoản hoặc mật khẩu sai')
        //     }
        // })
        if (value.username === "admin" && value.password === "admin") {
            message.success('Bạn đã đăng nhập thành công')
            // navigate("/");
        } else {
            message.error('Tài khoản hoặc mật khẩu sai')
        }
    };
    return (
        <div className="min-h-screen flex items-center justify-center bg-gray-900 p-4">
            <div className="flex flex-col lg:flex-row w-full max-w-5xl">
                <div className="w-full lg:w-1/2 bg-gray-800 p-6 lg:p-8 rounded-t-lg lg:rounded-l-lg lg:rounded-tr-none">
                    <h3 className="text-xl lg:text-2xl font-bold text-white mb-2">Đăng nhập</h3>
                    <p className="text-sm lg:text-base text-gray-400 mb-6">Bắt đầu trang web của bạn trong vài giây. Bạn
                        chưa có tài khoản? <Link to="/signup" className="text-blue-500 hover:underline">Đăng ký</Link>.
                    </p>
                    <Form onFinish={handleSubmit} layout="vertical" form={form}>
                        <div className="">
                            <Form.Item
                                label={<span className="text-sm font-medium text-gray-300">Email or User name</span>}
                                name="username"
                                className="flex-1"
                            >
                                <Input required placeholder="Mời bạn nhập vào đây"/>
                            </Form.Item>
                            <Form.Item
                                label={<span className="text-sm font-medium text-gray-300">Mật khẩu</span>}
                                name="password"
                                className="flex-1"
                            >
                                <Input.Password placeholder="••••••••"/>
                            </Form.Item>
                        </div>

                        <Form.Item>
                            <Button type="primary" htmlType="submit"
                                    className="w-full bg-blue-600 hover:bg-blue-700 rounded-md h-10 text-base font-medium">
                                Đăng nhập
                            </Button>
                        </Form.Item>
                    </Form>
                </div>
                <div
                    className="hidden lg:flex w-full lg:w-1/2 bg-gray-700 rounded-b-lg lg:rounded-r-lg lg:rounded-bl-none items-center justify-center p-8">
                    <img
                        src="https://flowbite.s3.amazonaws.com/blocks/marketing-ui/authentication/illustration.svg"
                        alt="Illustration"
                        className="max-w-full h-auto"
                    />
                </div>
            </div>
        </div>
    );
};

export default SignIn;