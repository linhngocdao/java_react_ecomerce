import {Button, Form, Input} from "antd";
import {Link} from "react-router-dom";

const SignUp = () => {
    return (
        <div className="min-h-screen flex items-center justify-center bg-gray-900 p-4">
            <div className="flex flex-col lg:flex-row w-full max-w-5xl">
                <div className="w-full lg:w-1/2 bg-gray-800 p-6 lg:p-8 rounded-t-lg lg:rounded-l-lg lg:rounded-tr-none">
                    <h3 className="text-xl lg:text-2xl font-bold text-white mb-2">Đăng ký</h3>
                    <p className="text-sm lg:text-base text-gray-400 mb-6">Nếu bạn đã có tài khoản hãy <Link to="/signin" className="text-blue-500 hover:underline">Đăng nhập</Link>.</p>
                    <Form
                        autoComplete="off"
                        layout="vertical"
                    >
                        <div className="">
                            <Form.Item
                                label={<span className="text-sm font-medium text-gray-300">Email or User name</span>}
                                name="username"
                                className="flex-1"
                            >
                                <Input required placeholder="Mời bạn nhập vào đây" />
                            </Form.Item>
                            <Form.Item
                                label={<span className="text-sm font-medium text-gray-300">Họ và tên</span>}
                                name="fullname"
                                className="flex-1"
                            >
                                <Input required placeholder="Mời bạn nhập vào đây" />
                            </Form.Item>
                            <Form.Item
                                label={<span className="text-sm font-medium text-gray-300">Mật khẩu</span>}
                                name="password"
                                className="flex-1"
                            >
                                <Input.Password  placeholder="••••••••" />
                            </Form.Item>
                            <Form.Item
                                label={<span className="text-sm font-medium text-gray-300">Nhập lại mật khẩu</span>}
                                name="cfpassword"
                                className="flex-1"
                            >
                                <Input.Password  placeholder="••••••••" />
                            </Form.Item>
                        </div>

                        <Form.Item>
                            <Button type="primary" htmlType="submit" className="w-full bg-blue-600 hover:bg-blue-700 rounded-md h-10 text-base font-medium mt-7">
                                Đăng ký ngay
                            </Button>
                        </Form.Item>
                    </Form>
                </div>
                <div className="hidden lg:flex w-full lg:w-1/2 bg-gray-700 rounded-b-lg lg:rounded-r-lg lg:rounded-bl-none items-center justify-center p-8">
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

export default SignUp;